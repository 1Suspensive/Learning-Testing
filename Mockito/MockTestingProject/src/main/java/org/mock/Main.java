package org.mock;

import org.mock.persistence.entity.repository.IPlayerRepository;
import org.mock.persistence.entity.repository.PlayerRepositoryImpl;
import org.mock.services.IPlayerService;
import org.mock.services.PlayerServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();
        IPlayerService playerService = new PlayerServiceImpl(playerRepository);

        playerService.deleteById(1L);
        System.out.println(playerService.findAll());
    }
}