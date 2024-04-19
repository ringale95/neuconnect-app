package edu.neu.neuconnect.model;

import lombok.Data;
import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@PrimaryKeyJoinColumn(name="multiple_id")
public class MultipleServiceRequest extends ServiceRequest{
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="ParticipantsMSR", joinColumns=@JoinColumn(name="msr_id"), inverseJoinColumns=@JoinColumn(name="participant_id"))

    private List<User> participants = new ArrayList<>();

    public void addParticipant(User participant){
         participants.add(participant);
    }
}
