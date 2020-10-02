/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import domain.*;
/**
 *
 * @author 839645
 */
public class NoteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String edit = request.getParameter("edit");
        String filepath = this.getServletContext().getRealPath("/WEB-INF/notes.txt");

        if(edit != null){
            
            request.setAttribute("note", this.getNoteById(filepath,Integer.parseInt(edit)) );            
            this.getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request,response);   
            
        } else {
            ArrayList<Note> notes = this.getNotes(filepath);
            String[] content;
            for(Note n: notes){
                String items="";
                content = n.getContent().split("\\n");
                for(String c: content){
                   items += "<br>"+c; 
                }
                
                n.setContent(items);
            }
            request.setAttribute("notes", notes );
            this.getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request, response);
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Note curNode = new Note(Integer.parseInt(id),title,content);
        String url;
        if(title.trim().equals("") || title == null || content.trim().equals("") || content==null){
            request.setAttribute("invalid",true);
            request.setAttribute("note",curNode);
            url = "/WEB-INF/editnote.jsp";
            this.getServletContext().getRequestDispatcher(url).forward(request, response);
            
        }else{
            url = "/WEB-INF/viewnote.jsp";
            String filepath = this.getServletContext().getRealPath("/WEB-INF/notes.txt");
            this.setNoteById(filepath, Integer.parseInt(id), curNode);

           this.doGet(request, response);
        }
 }
    private Note getNoteById(String filepath, int id){
        ArrayList<Note> notes = this.getNotes(filepath);
        return notes.get(id-1);
    }
    
    private void setNoteById(String filepath, int id, Note newNote){
        ArrayList<Note> notes = this.getNotes(filepath);
        String[] contentLines;
        notes.set(id-1, newNote);
        
        try( PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))){
            
            for(int i=0; i<notes.size(); i++){
                write.println(notes.get(i).getId());
                write.println(notes.get(i).getTitle());
                contentLines = notes.get(i).getContent().split("\n");
                for(String contentLine: contentLines){
                    if(!contentLine.trim().equals(""))
                        write.println(contentLine.trim());

                }
                write.println("&");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    private ArrayList<Note> getNotes(String filepath){
        ArrayList<Note> notes= new ArrayList<>();
        Note curNote;
        int id;
        String title;
        String content;
        String line;
        try {   
           BufferedReader read = new BufferedReader(new FileReader(filepath));
                   
            while((line = read.readLine()) != null){
                id = Integer.parseInt(line);
                title = read.readLine();
                content="";
                while(!(line = read.readLine()).trim().equals("&")){

                    content +=line+"\n";
                }
                
                // Adds Note to ArrayList
                notes.add(new Note(id,title,content));
                
            }
            
            read.close();
            
        } catch (IOException | NumberFormatException ex) {
    ex.printStackTrace();
    //Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return notes;
    }


}
