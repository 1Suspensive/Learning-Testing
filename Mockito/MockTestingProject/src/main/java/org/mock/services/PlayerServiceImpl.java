package org.mock.services;

import org.mock.persistence.entity.Player;
import org.mock.persistence.entity.repository.PlayerRepositoryImpl;

import java.util.List;

public class PlayerServiceImpl implements IPlayerService{

    private final PlayerRepositoryImpl playerRepository;

    public PlayerServiceImpl(PlayerRepositoryImpl playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void save(Player player) {
         playerRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }
}
