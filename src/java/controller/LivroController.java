package controller;

import dao.LivroDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Livro;
import static util.Util.formatarDataParaBanco;

@WebServlet(name = "LivroController", urlPatterns = {"/LivroController"})
public class LivroController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERIR_OU_EDITAR = "/livro.jsp";
    private static String LISTA_DE_LIVROS = "/listaDeLivros.jsp";
    private LivroDao dao;

    public LivroController() {
        super();
        dao = new LivroDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("apagar")){
            int livroId = Integer.parseInt(request.getParameter("id"));
            dao.apagarLivro(livroId);
            forward = LISTA_DE_LIVROS;
            request.setAttribute("livros", dao.buscarTotosOsLivros());    
        } else if (action.equalsIgnoreCase("editar")){
            forward = INSERIR_OU_EDITAR;
            int livroId = Integer.parseInt(request.getParameter("id"));
            Livro livro = dao.buscarLivroPorId(livroId);
            request.setAttribute("livro", livro);
        } else if (action.equalsIgnoreCase("listaDeLivros")){
            forward = LISTA_DE_LIVROS;
            request.setAttribute("livros", dao.buscarTotosOsLivros());
        } else {
            forward = INSERIR_OU_EDITAR;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Livro livro = new Livro();
        
        livro.setNome(request.getParameter("nome"));
        livro.setAutor(request.getParameter("autor"));
        livro.setDatalancamento(formatarDataParaBanco(request.getParameter("dataLancamento")));
       
        String livroId = request.getParameter("id");
        
        if(livroId == null || livroId.isEmpty()) {
            dao.adicionarLivro(livro);
        } else {
            livro.setId(Integer.parseInt(livroId));
            dao.atualizarLivros(livro);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LISTA_DE_LIVROS);
        request.setAttribute("livros", dao.buscarTotosOsLivros());
        view.forward(request, response);
    }
}
