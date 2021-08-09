package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {


    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }


//    private final TrelloClient trelloClient;

//    private final TrelloService trelloService;
//
//    private final TrelloFacade trelloFacade;
//
//    @GetMapping("/getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloFacade.fetchTrelloBoards();
//    }
//
//    @PostMapping("/createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloFacade.createCard(trelloCardDto);
//    }


//    @PostMapping("/createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//        return trelloClient.createNewCard(trelloCardDto);
//    }
//
//    @GetMapping("/getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloClient.getTrelloBoards();
//    }

//    public void getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//        trelloBoards.forEach(trelloBoardDto -> {
//            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList -> {
//                System.out.println(
//                        trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()
//                );
//            });
//
//        });
//    }

}