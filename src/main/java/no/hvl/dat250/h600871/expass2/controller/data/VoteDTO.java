package no.hvl.dat250.h600871.expass2.controller.data;

import no.hvl.dat250.h600871.expass2.model.Vote;
import no.hvl.dat250.h600871.expass2.model.VoteOption;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public record VoteDTO(
        Optional<Long> id,
        Optional<Instant> publishedAt,
        Optional<String> username,
        Optional<List<VoteOptionEntry>> voteOptions
) {


    public static VoteDTO voteOptions(Vote vote) {
        return new VoteDTO(
                Optional.of(vote.getId()),
                Optional.ofNullable(vote.getPublishedAt()),
                Optional.of(vote.getUser().getUsername()),
                Optional.of(vote.getVoteOption().stream().map(VoteOptionEntry::new).toList()));
    }

    public record VoteOptionEntry(long id, String caption) {
        VoteOptionEntry(VoteOption voteOption) {
            this(voteOption.getId(), voteOption.getCaption());
        }
    }
}
