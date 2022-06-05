package il.cshaifasweng.OCSFMediatorExample.server;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public String complaint;
    @ManyToOne(fetch = FetchType.LAZY)
    public User issuedBy;
}
