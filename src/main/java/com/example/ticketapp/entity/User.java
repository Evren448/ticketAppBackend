package com.example.ticketapp.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "users")
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "fullname", nullable = false)
	private String fullname;
	
	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
	private Role role;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> ticketList;
	
    @Transient
    private String token;

	
}
