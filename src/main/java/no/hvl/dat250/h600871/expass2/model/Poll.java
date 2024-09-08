package no.hvl.dat250.h600871.expass2.model;

import java.time.Instant;
import java.util.List;

public record Poll(
        String question,
        Instant publishedAt,
        Instant validUntil,
        User author,
        List<VoteOption> voteOptions
) { }
