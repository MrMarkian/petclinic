package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VisitMapService extends  AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit thisVisit) {
        //Validate object before saving.
        if (thisVisit.getPet() == null
                || thisVisit.getPet().getOwner() == null
                || thisVisit.getPet().getId() == null
                || thisVisit.getPet().getOwner().getId() == null)
        {
            throw new RuntimeException("Invalid / Null Visit.");
        }

        return super.save(thisVisit);
    }

    @Override
    public void delete(Visit thisVisit) {
        super.delete(thisVisit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }
}

