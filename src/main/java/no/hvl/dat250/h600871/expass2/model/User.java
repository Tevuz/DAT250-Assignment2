package no.hvl.dat250.h600871.expass2.model;

import java.util.List;

public record User(
        String username,
        String email,
        List<Poll> polls,
        List<Vote> votes
) { }
