package org.example.blogdemensajes.models;


import jakarta.persistence.*;

@Entity
@Table(name = "MESSAGES")
@NamedQueries({
        @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
        @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
        @NamedQuery(name = "Message.findBySender", query = "SELECT m FROM Message m WHERE m.sender.id = :senderId")
})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MESSAGE_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private User sender;
    private String content;

    public Message(){
        // JPA requirement
    }

    public Message(String content, User user) {
        this.content = content;
        this.sender = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User user) {
        this.sender = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
