package org.mock.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mock.DataProvider;
import org.mock.persistence.entity.Player;
import org.mock.persistence.entity.repository.PlayerRepositoryImpl;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.List;

//MOCKITO SOLO TRABAJA CON LAS DEPENDENCIAS

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepositoryImpl playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testFindAll(){

        //When
        when(playerRepository.findAll()).thenReturn(DataProvider.playerListMock());

        List<Player> result = playerService.findAll();

        //Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Lionel Messi", result.get(0).getName());
        assertEquals("Inter Miami", result.get(0).getTeam());
        verify(this.playerRepository).findAll();
    }

    @Test
    public void testFindById(){

        Long id = 1L;

        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.playerMock());
        Player result = playerService.findById(id);

        assertNotNull(result);
        assertEquals("Lionel Messi", result.getName());
        verify(this.playerRepository,times(1)).findById(anyLong());
    }

    @Test
    public void testSave(){
        Player player = DataProvider.newPlayerMock();

        this.playerService.save(player);

        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        verify(this.playerRepository).save(playerCaptor.capture());
        assertEquals(7L, playerCaptor.getValue().getId());
    }

    @Test
    public void testDeleteById(){
        Long id = 1L;

        this.playerService.deleteById(id);

        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository).deleteById(anyLong());
        verify(this.playerRepository).deleteById(longArgumentCaptor.capture());

        assertEquals(1L, longArgumentCaptor.getValue());

    }
}
