package no.hvl.dat250.h600871.expass2.model;

import java.util.List;

public record VoteOption(
    String caption,
    int presentationOrder,
    Poll poll,
    List<Vote> votes
) { }
