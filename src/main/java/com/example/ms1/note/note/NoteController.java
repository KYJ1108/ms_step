package com.example.ms1.note.note;

import com.example.ms1.note.notebook.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books/{notebookId}/notes")
public class NoteController {

    private final NoteRepository noteRepository;
    private final NotebookRepository notebookRepository;
    private final NoteService noteService;

    @PostMapping("/write")
    public String write() {
        noteService.saveDefault();
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Note note = noteRepository.findById(id).get();
        model.addAttribute("targetNote", note);
        model.addAttribute("noteList", noteRepository.findAll());

        return "main";
    }
    @PostMapping("/{id}/update")
    public String update(Long id, String title, String content) {
        Note note = noteRepository.findById(id).get();
        note.setTitle(title);
        note.setContent(content);

        noteRepository.save(note);
        return "redirect:/detail/" + id;
    }
}
