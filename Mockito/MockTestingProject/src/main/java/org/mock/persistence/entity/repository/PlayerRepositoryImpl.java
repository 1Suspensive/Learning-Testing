package org.mock.persistence.entity.repository;

import org.mock.persistence.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerRepositoryImpl implements IPlayerRepository{

    private List<Player> playerDatabase = new ArrayList<>(List.of(
            new Player(1L, "Lionel Messi", "Inter Miami", "Delantero"),
            new Player(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
            new Player(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
            new Player(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
            new Player(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
            new Player(6L, "Virgil van Dijk", "Liverpool", "Defensa")
    ));

    @Override
    public List<Player> findAll() {
        System.out.println("Metodo findAll real");
        return playerDatabase;
    }

    @Override
    public Player findById(long id) {
        System.out.println("Metodo findById real");
        return playerDatabase.stream()
                .filter(player -> player.getId().equals(id)).findFirst().orElseThrow();
    }

    @Override
    public void save(Player player) {
        System.out.println("Metodo save real");
        playerDatabase.add(player);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Metodo delete real");
        this.playerDatabase = playerDatabase.stream()
                .filter(player -> player.getId()!=id)
                .collect(Collectors.toList());
    }
}
