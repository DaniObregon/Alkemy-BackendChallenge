package com.danio.alkemy.service;

import com.danio.alkemy.entity.Character;
import com.danio.alkemy.exception.CharacterNotFoundException;
import com.danio.alkemy.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Character findCharacterById(Long id) throws CharacterNotFoundException {
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id));
    }

    public Character saveCharacter(Character character) {
        return characterRepository.save(character);
    }

    public List<Character> findAllCharacters() {
        return characterRepository.findAll();
    }

    @Transactional
    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    @Transactional
    public Character editCharacter(Long id, Character character) {
        Character characterToEdit = findCharacterById(id);
        characterToEdit.setName(character.getName());
        characterToEdit.setAge(character.getAge());
        characterToEdit.setWeight(character.getWeight());
        characterToEdit.setStory(character.getStory());
        return characterToEdit;
    }
}
