package pl.sda.hibernate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double wartosc;
    @CreationTimestamp
    private LocalDateTime dataCzasDodania;

    @Enumerated(value = EnumType.STRING)
    private Przedmiot przedmiot;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Student uczen;
}
