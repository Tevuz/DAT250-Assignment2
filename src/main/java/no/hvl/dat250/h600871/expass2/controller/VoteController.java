package no.hvl.dat250.h600871.expass2.controller;

import no.hvl.dat250.h600871.expass2.controller.data.VoteDTO;
import no.hvl.dat250.h600871.expass2.model.User;
import no.hvl.dat250.h600871.expass2.model.Vote;
import no.hvl.dat250.h600871.expass2.model.VoteOption;
import no.hvl.dat250.h600871.expass2.service.Exceptions.VoteException;
import no.hvl.dat250.h600871.expass2.service.UserService;
import no.hvl.dat250.h600871.expass2.service.VoteOptionService;
import no.hvl.dat250.h600871.expass2.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/votes")
public class VoteController {

    private VoteService voteService;
    private UserService userService;
    private VoteOptionService optionService;

    public VoteController(@Autowired VoteService service, @Autowired UserService userService, @Autowired VoteOptionService optionService) {
        this.voteService = service;
        this.userService = userService;
        this.optionService = optionService;
    }

    @PostMapping("")
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteInfo) {
        if (voteInfo.username().isEmpty() || voteInfo.voteOptions().isEmpty())
            return ResponseEntity.badRequest().build();

        Optional<User> user_entry = userService.readUserByUsername(voteInfo.username().get());
        List<VoteOption> voteOptions = voteInfo.voteOptions().get().stream().map(v -> v.id()).map(optionService::readVoteOptionById).filter(Optional::isPresent).map(Optional::get).toList();

        Vote vote = new Vote();
        voteInfo.publishedAt().ifPresent(vote::setPublishedAt);
        vote.setUser(user_entry.get());
        vote.setVoteOption(voteOptions);

        vote = voteService.createVote(vote);

        return ResponseEntity.created(URI.create("/"+vote.getId())).body(VoteDTO.voteOptions(vote));
    }

    @GetMapping("")
    public ResponseEntity<List<VoteDTO>> getVotes() {
        return ResponseEntity.ok(voteService.readAllVotes().stream().map(VoteDTO::voteOptions).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable long id) {
        Optional<Vote> vote = voteService.readVoteById(id);
        if (vote.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(VoteDTO.voteOptions(vote.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteDTO> updateVote(@PathVariable long id, @RequestBody VoteDTO voteInfo) {
        Optional<Vote> vote_entry = voteService.readVoteById(id);
        if (vote_entry.isEmpty())
            return ResponseEntity.notFound().build();

        Vote vote = vote_entry.get();
        voteInfo.publishedAt().ifPresent(vote::setPublishedAt);

        if (voteInfo.username().isPresent()) {
            Optional<User> user = userService.readUserByUsername(voteInfo.username().get());
            if (user.isEmpty())
                return ResponseEntity.notFound().build();
            vote.setUser(user.get());
        }

        voteInfo.voteOptions().ifPresent(list -> {
            List<VoteOption> options = vote.getVoteOption();
            options.clear();
            list.stream().map(v -> v.id()).map(optionService::readVoteOptionById).filter(Optional::isPresent).map(Optional::get).forEach(options::add);
        });

       return ResponseEntity.ok(VoteDTO.voteOptions(voteService.updateVote(vote)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVote(@PathVariable long id) {
        try {
            voteService.deleteVoteById(id);
            return ResponseEntity.noContent().build();
        } catch (VoteException exception) {
            return ResponseEntity.notFound().build();
        }
    }


}
