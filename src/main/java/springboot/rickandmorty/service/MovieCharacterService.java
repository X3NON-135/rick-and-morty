package springboot.rickandmorty.service;

import java.util.List;
import springboot.rickandmorty.model.MovieCharacter;

public interface MovieCharacterService {
    MovieCharacter getRandomCharacter();

    List<MovieCharacter> findAllByNameContains(String namePart);
}
