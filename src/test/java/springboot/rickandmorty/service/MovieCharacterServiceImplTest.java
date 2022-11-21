package springboot.rickandmorty.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.rickandmorty.dto.external.ApiCharacterDto;
import springboot.rickandmorty.dto.external.ApiResponseDto;
import springboot.rickandmorty.dto.mapper.MovieCharacterMapper;
import springboot.rickandmorty.model.Gender;
import springboot.rickandmorty.model.MovieCharacter;
import springboot.rickandmorty.model.Status;
import springboot.rickandmorty.repository.MovieCharacterRepository;

@ExtendWith(MockitoExtension.class)
class MovieCharacterServiceImplTest {
    @InjectMocks
    private MovieCharacterServiceImpl movieCharacterService;
    @Mock
    private MovieCharacterRepository repository;
    @Mock
    private MovieCharacterMapper mapper;

    @Test
    void saveDtoToDb_Ok() {
        ApiCharacterDto rickSanchez = new ApiCharacterDto();
        rickSanchez.setId(1L);
        rickSanchez.setName("Rick Sanchez");
        rickSanchez.setStatus("ALIVE");
        rickSanchez.setGender("MALE");
        ApiCharacterDto mortySmith = new ApiCharacterDto();
        mortySmith.setId(21L);
        mortySmith.setName("Aqua Morty");
        mortySmith.setStatus("UNKNOWN");
        mortySmith.setGender("MALE");

        MovieCharacter rickSanchezFromDb = new MovieCharacter();
        rickSanchezFromDb.setExternalId(rickSanchez.getId());
        rickSanchezFromDb.setName(rickSanchez.getName());
        rickSanchezFromDb.setStatus(Status.valueOf(rickSanchez.getStatus()));
        rickSanchezFromDb.setGender(Gender.valueOf(rickSanchez.getGender()));
        MovieCharacter mortySmithFromDb = new MovieCharacter();
        mortySmithFromDb.setExternalId(mortySmith.getId());
        mortySmithFromDb.setName(mortySmith.getName());
        mortySmithFromDb.setStatus(Status.valueOf(mortySmith.getStatus()));
        mortySmithFromDb.setGender(Gender.valueOf(mortySmith.getGender()));

        Set<Long> externalIds = new HashSet<>();
        externalIds.add(rickSanchez.getId());
        externalIds.add(mortySmith.getId());

        ApiResponseDto responseDto = new ApiResponseDto();
        responseDto.setInfo(null);
        responseDto.setResults(new ApiCharacterDto[] {rickSanchez, mortySmith});

        List<MovieCharacter> charactersToSave = List.of(rickSanchezFromDb, mortySmithFromDb);
        List<MovieCharacter> expected = List.of(rickSanchezFromDb);

        Mockito.when(repository.findAllByExternalIdIn(externalIds)).thenReturn(new ArrayList<>());
        Mockito.when(repository.saveAll(charactersToSave)).thenReturn(expected);
        Mockito.when(mapper.parseApiCharacterResponseDto(rickSanchez)).thenReturn(rickSanchezFromDb);
        Mockito.when(mapper.parseApiCharacterResponseDto(mortySmith)).thenReturn(mortySmithFromDb);

        List<MovieCharacter> actual = movieCharacterService.saveDtoToDB(responseDto);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }
}