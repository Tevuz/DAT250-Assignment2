package no.hvl.dat250.h600871.expass2.repository;

import no.hvl.dat250.h600871.expass2.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> { }
