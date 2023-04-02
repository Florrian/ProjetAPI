package main.java.org.miage.candidat.control;

import org.miage.candidat.boundary.CandidatRepresentation;
import org.miage.candidat.entity.Candidat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import org.miage.conversionservice.entity.DeviseConversionBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;

import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CandidatAssembler implements RepresentationModelAssembler<Candidat, EntityModel<Candidat>> {

    @Override
    public EntityModel<Candidat> toModel(Candidat Candidat) {
        return EntityModel.of(candidat,
                linkTo(methodOn(CandidatRepresentation.class).getCandidat(candidat.getIdCandidat())).withSelfRel(),
                linkTo(methodOn(CandidatRepresentation.class).getAllCandidat()).withRel("collection"));
    }

    @Override
    public CollectionModel<EntityModel<Candidat>> toCollectionModel(Iterable<? extends Candidat> entities) {
        var CandidatModel = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .toList();
        return CollectionModel.of(OffreStageModel,
                linkTo(methodOn(CandidatRepresentation.class).getAllCandidat()).withSelfRel());
    }
}
