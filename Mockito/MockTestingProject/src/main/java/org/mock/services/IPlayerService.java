package org.mock.services;

import org.mock.persistence.entity.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> findAll();
    Player findById(long id);
    void save(Player player);
    void deleteById(Long id);
}
