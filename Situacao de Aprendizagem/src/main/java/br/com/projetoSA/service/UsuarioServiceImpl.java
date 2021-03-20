package br.com.projetoSA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetoSA.model.Usuario;
import br.com.projetoSA.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public String saveAddUsuario(Usuario usuario) {

        if (usuarioRepository.findByLogin(usuario.getLogin()) == null && usuarioRepository.findByCpf(usuario.getCpf()) == null) {

			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			
			usuarioRepository.save(usuario);
			
			return "redirect:/login";

		} else {
			
			System.out.println("Este usuário já existe!");

			return "redirect:/usuario/add";
		}
    }

    @Override
    public String saveEditUsuario(Usuario usuario) {

        try {

			usuarioRepository.save(usuario);

			return "redirect:/usuario/view";

		} catch (Exception e) {
			
			System.out.println("ERRO: " + e);

			return "redirect:/usuario/edit";

		}    
    }

    @Override
    public String deleteUsuario(String login) {

        try {

			usuarioRepository.deleteByLogin(login);

			return "redirect:/usuario/add";

		} catch (Exception e) {
			
			System.out.println("ERRO: " + e);

			return "redirect:/usuario/view";

		}
    }
}