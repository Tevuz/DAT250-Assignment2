package no.hvl.dat250.h600871.expass2.model;

import java.time.Instant;
import java.util.Set;

public record Vote(
    Instant publishedAt,
    User user,
    Set<VoteOption> voteOptions
) { }
