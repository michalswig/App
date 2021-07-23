package com.crud.tasks.service;

import com.crud.tasks.domain.AttachmentsByType;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Trello;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NeedToCoverTestSuite {

    @Test
    public void coverTestSuite(){
        //Given
        AttachmentsByType attachmentsByType = new AttachmentsByType();
        Badges badges = new Badges();
        Trello trello = new Trello();
        CreatedTrelloCardDto trelloCardDto = new CreatedTrelloCardDto();
        trello.setBoard(1);
        trello.setCard(1);
        attachmentsByType.setTrello(trello);
        badges.setAttachmentsByType(attachmentsByType);
        badges.setVotes(10);
        trelloCardDto.setId("1");
        trelloCardDto.setName("testName");
        trelloCardDto.setShortUrl("testUrl");
        //When
        //Then
        assertEquals(attachmentsByType.getTrello().getBoard(), 1);
        assertEquals(attachmentsByType.getTrello().getCard(), 1);
        assertEquals(badges.getVotes(), 10);
        assertEquals(badges.getAttachmentsByType().getTrello().getBoard(), 1);
        assertEquals(badges.getAttachmentsByType().getTrello().getCard(), 1);
        assertEquals(trelloCardDto.getId(), 1);
        assertEquals(trelloCardDto.getName(), "testName");
        assertEquals(trelloCardDto.getShortUrl(), "testUrl");
    }

}
