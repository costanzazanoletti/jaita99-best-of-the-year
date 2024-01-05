package org.learning.bestoftheyear.controller;

import java.util.ArrayList;
import java.util.List;
import org.learning.bestoftheyear.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongController {

  // metodo che restituisce la pagina con la lista delle Song
  @GetMapping
  public String list(Model model) {
    model.addAttribute("songList", getSongs());
    return "songs/songList";
  }


  // metodo che restituisce la pagina di dettaglio della Song presa per id
  @GetMapping("/details/{id}") // http://localhost:8080/songs/details/1 --> 1
  public String detail(@PathVariable(name = "id") int songId, Model model) {
    // recupero la Song presa per id
    Song song = getSongById(songId);
    model.addAttribute("songId", songId);
    model.addAttribute("song", song);
    return "songs/songDetail";
  }
/*
    Versione alternativa con @RequestParam invece che @PathVariable
  @GetMapping("/details") // http://localhost:8080/songs/details?id=1 --> 1
  public String detailRequestParam(@RequestParam(name = "id") int songId, Model model) {
    Song song = getSongById(songId);
    model.addAttribute("songId", songId);
    model.addAttribute("song", song);
    return "songs/songDetail";
  }
*/

  // metodo per simulare di recuperare una lista di Song da database
  private List<Song> getSongs() {
    List<Song> songList = new ArrayList<>();
    songList.add(new Song(1, "FirstSong", "FirstAuthors"));
    songList.add(new Song(2, "SecondSong", "SecondAuthors"));
    songList.add(new Song(3, "ThirdSong", "ThirdAuthors"));
    return songList;
  }

  // metodo per ricercare una Song per id nella lista di songs
  private Song getSongById(int id) {
    List<Song> songList = getSongs();
    for (Song song : songList) {
      if (song.getId() == id) {
        return song;
      }
    }
    return null;
  }
}
