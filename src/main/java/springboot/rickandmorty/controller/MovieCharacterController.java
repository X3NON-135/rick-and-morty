package springboot.rickandmorty.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.rickandmorty.dto.CharacterResponseDto;
import springboot.rickandmorty.dto.mapper.MovieCharacterMapper;
import springboot.rickandmorty.model.MovieCharacter;
import springboot.rickandmorty.service.MovieCharacterService;

@RestController
@RequestMapping("/movie-characters")
public class MovieCharacterController {
    private final MovieCharacterService characterService;
    private final MovieCharacterMapper mapper;

    public MovieCharacterController(MovieCharacterService characterService,
                                    MovieCharacterMapper mapper) {
        this.characterService = characterService;
        this.mapper = mapper;
    }

    @GetMapping("/fetch")
    @ApiOperation(value = "fetch all characters from API, sync and save it to DB")
    public String syncAndSaveAllToDb() {
        characterService.syncExternalCharacters();
        return "Done!";
    }

    @GetMapping("/random")
    @ApiOperation(value = "get random character")
    public Object getRandom() {
        MovieCharacter randomCharacter = characterService.getRandomCharacter();
        return mapper.toResponseDto(randomCharacter);
    }

    @GetMapping("/by-name")
    @ApiOperation(value = "get any character by name or it's part")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namaPart) {
        return characterService.findAllByNameContains(namaPart).stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
