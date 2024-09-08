package no.hvl.dat250.h600871.expass2.repository;

import no.hvl.dat250.h600871.expass2.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> { }
