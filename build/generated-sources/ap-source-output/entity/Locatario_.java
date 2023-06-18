package entity;

import entity.Locacao;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-06-18T13:28:57", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Locatario.class)
public class Locatario_ { 

    public static volatile SingularAttribute<Locatario, String> contaBancaria;
    public static volatile CollectionAttribute<Locatario, Locacao> locacaoCollection;
    public static volatile SingularAttribute<Locatario, String> cpf;
    public static volatile SingularAttribute<Locatario, String> nome;
    public static volatile SingularAttribute<Locatario, String> estadoCivil;
    public static volatile SingularAttribute<Locatario, Date> dataNascimento;
    public static volatile SingularAttribute<Locatario, Integer> idLocatario;

}