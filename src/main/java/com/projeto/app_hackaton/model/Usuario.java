package com.projeto.app_hackaton.model;

import com.projeto.app_hackaton.model.enums.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Usuario implements UserDetails {

    // Principais Atributos:

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TipoUsuario tipo = TipoUsuario.PARTICIPANTE;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private Equipe equipe;

    @OneToMany(mappedBy = "mentor")
    private List<Avaliacao> avaliacoes;

    // Metodos obrigatorios da interface UserDetails do spring security:

    // Metodo de retorno das autoridades de um usuário dentro do sistema...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipo == TipoUsuario.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (this.tipo == TipoUsuario.MENTOR){
            return List.of(new SimpleGrantedAuthority("ROLE_MENTOR"));
        }else {
            return List.of(new SimpleGrantedAuthority("ROLE_PARTICIPANTE"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
