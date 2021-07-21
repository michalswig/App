package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {
    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapBoardsDtoToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        trelloBoardDto.setId("1");
        trelloBoardDto.setName("testNameBoard");
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto();
        trelloListDto.setId("1");
        trelloListDto.setName("testNameList");
        trelloListDto.setClosed(true);
        ArrayList<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(0, trelloListDto);
        trelloBoardDto.setLists(trelloListsDto);
        trelloBoardsDto.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(trelloBoards.get(0).getId(), "1");
        assertEquals(trelloBoards.get(0).getName(), "testNameBoard");
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(), "1");
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), "testNameList");
        assertTrue(trelloBoards.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapBoardsToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList();
        trelloList.setId("1");
        trelloList.setName("testNameList");
        trelloList.setClosed(true);

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard();
        trelloBoard.setId("1");
        trelloBoard.setName("testNameBoard");
        trelloBoard.setLists(trelloLists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoards.get(0).getId(), "1");
        assertEquals(trelloBoardsDto.get(0).getName(), "testNameBoard");
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(), "1");
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), "testNameList");
        assertTrue(trelloBoardsDto.get(0).getLists().get(0).isClosed());

    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto();
        trelloListDto.setId("1");
        trelloListDto.setName("testNameList");

        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        assertEquals(trelloLists.get(0).getId(), "1");
        assertEquals(trelloLists.get(0).getName(), "testNameList");

    }

    @Test
    public void testToListDto() {
        //Given
        TrelloList trelloList = new TrelloList();
        trelloList.setId("1");
        trelloList.setName("testNameList");

        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertEquals(trelloListsDto.get(0).getId(), "1");
        assertEquals(trelloListsDto.get(0).getName(), "testNameList");
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        trelloCardDto.setName("testName");
        trelloCardDto.setDescription("testDescription");
        trelloCardDto.setPos("testPos");
        trelloCardDto.setListId("testListId");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getName(), "testName");

    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard();
        trelloCard.setName("testName");
        trelloCard.setDescription("testDescription");
        trelloCard.setPos("testPos");
        trelloCard.setListId("testListId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), "testName");

    }


}
