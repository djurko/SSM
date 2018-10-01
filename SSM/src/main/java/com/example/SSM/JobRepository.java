package com.example.SSM;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobRepository extends PagingAndSortingRepository<Job, Long> {

}
