package controle;

import excecao.DevolucaoInvalidaException;
import excecao.LimiteEmprestimosExcedidoException;
import excecao.UsuarioNaoCadastradoException;
import modelo.Livro;
import modelo.Usuario;
import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    public static final int MIN_COPIAS_PARA_PODER_EMPRESTAR = 2;
    public static final int MAX_LIVROS_DEVIDOS = 3;

    private Map<Long,Usuario> mapaUsuarioCpf;
    private Map<Livro,Integer> mapaQuantidadeLivros;
    private Map<Usuario,Integer> mapaEmprestimo;

    public Biblioteca() {
        mapaUsuarioCpf = new HashMap<>();
        mapaQuantidadeLivros = new HashMap<>();
        mapaEmprestimo = new HashMap<>();
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (!mapaUsuarioCpf.containsKey(usuario.getCpf())){
            mapaUsuarioCpf.put(usuario.getCpf(),usuario);
            mapaEmprestimo.put(usuario,0);
        }

        else if (mapaUsuarioCpf.containsKey(usuario.getCpf())){
            mapaUsuarioCpf.remove(usuario.getCpf());
            mapaUsuarioCpf.put(usuario.getCpf(),usuario);
        }
    }

    public Usuario getUsuario(long cpf) { return mapaUsuarioCpf.get(cpf); }

    public int getTotalDeUsuariosCadastrados() { return mapaUsuarioCpf.size(); }

    public void incluirLivroNoAcervo(Livro livro, int quantidade) {
        if(mapaQuantidadeLivros.containsKey(livro))
            mapaQuantidadeLivros.replace(livro,mapaQuantidadeLivros.get(livro));

        else if(!mapaQuantidadeLivros.containsKey(livro))  mapaQuantidadeLivros.put(livro,quantidade);
    }

    public boolean emprestarLivro(Livro livro, Usuario usuario)
            throws UsuarioNaoCadastradoException, LimiteEmprestimosExcedidoException {

        if (!mapaUsuarioCpf.containsKey(usuario.getCpf()))  throw new UsuarioNaoCadastradoException();

        else if (!mapaQuantidadeLivros.containsKey(livro))  return false;

        else if (mapaEmprestimo.get(usuario)>=MAX_LIVROS_DEVIDOS)  throw new LimiteEmprestimosExcedidoException();

        else if(mapaQuantidadeLivros.get(livro)>=MIN_COPIAS_PARA_PODER_EMPRESTAR){
            mapaQuantidadeLivros.replace(livro,mapaQuantidadeLivros.get(livro),mapaQuantidadeLivros.get(livro)-1);
            mapaEmprestimo.replace(usuario,mapaEmprestimo.get(usuario),mapaEmprestimo.get(usuario)+1);
            usuario.emprestarLivro(livro);

            return true;
        }
        return false;
    }

    public void receberDevolucaoLivro(Livro livro, Usuario usuario) throws DevolucaoInvalidaException {
        if (usuario.possuiObjeto(livro)){
            usuario.devolverLivro(livro);
            mapaEmprestimo.replace(usuario,mapaEmprestimo.get(usuario),mapaEmprestimo.get(usuario) - 1);
            mapaQuantidadeLivros.replace(livro,mapaQuantidadeLivros.get(livro),mapaQuantidadeLivros.get(livro) + 1);

        }

        else if (!usuario.possuiObjeto(livro))  throw new DevolucaoInvalidaException();
    }

    public int getQuantidadeDeLivrosDevidos(Usuario usuario) {
        if (mapaEmprestimo.containsKey(usuario) && mapaEmprestimo.get(usuario)>0)  return mapaEmprestimo.get(usuario);

        return 0;
    }

    public int getQuantidadeDeLivrosNaEstante() {
        int cont = 0;

        for(Livro livroGenerico: mapaQuantidadeLivros.keySet())  cont+= mapaQuantidadeLivros.get(livroGenerico);

        return cont;
    }

    public int getQuantidadeDeLivrosNaEstante(Livro livro) {
        if (mapaQuantidadeLivros.containsKey(livro))  return mapaQuantidadeLivros.get(livro);

        return 0;
    }
}