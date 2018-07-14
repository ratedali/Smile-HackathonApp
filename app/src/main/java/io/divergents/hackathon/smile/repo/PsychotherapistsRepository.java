package io.divergents.hackathon.smile.repo;

import android.arch.lifecycle.LiveData;
import java.util.List;
import io.divergents.hackathon.smile.data.Psychotherapist;

public interface PsychotherapistsRepository {
    LiveData<List<Psychotherapist>> getPsychotherapists();

    LiveData<Psychotherapist> getPsychotherapist(String id);

    LiveData<Psychotherapist> getDesignatedPsychotherapist();
}
