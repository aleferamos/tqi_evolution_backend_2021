package br.com.tqi.analiseemprestimo.services;

import br.com.tqi.analiseemprestimo.data.DetalheClienteData;
import br.com.tqi.analiseemprestimo.models.Cliente;
import br.com.tqi.analiseemprestimo.repositories.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheClienteService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public DetalheClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);

        if(cliente.isEmpty()){
            throw new UsernameNotFoundException("Cliente [" + email + "] não encontrado");
        }
        return new DetalheClienteData(cliente);
    }
}
