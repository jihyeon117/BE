package com.example.zzan.room.entity;

import com.example.zzan.global.Timestamped;
import com.example.zzan.room.dto.RoomRequestDto;
import com.example.zzan.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "TB_ROOM")
@Getter
@NoArgsConstructor
public class Room extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @NotNull(message = "방제목을 입력해주세요.")
    private String title;

    private String username;

    //private String image;

    @NotNull(message = "카테고리를 설정해주세요.")
    private String category;

//    private String runningTime;

    private String genderSetting;

    private Boolean isPrivate;

    private String roomPassword;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    public Room(RoomRequestDto roomRequestDto){
        this.title = roomRequestDto.getTitle();
    }

    public Room(RoomRequestDto roomRequestDto, User user) {
        this.title = roomRequestDto.getTitle();
        this.user = user;
        this.username = user.getUsername();
        this.category = roomRequestDto.getCategory();
        this.genderSetting = roomRequestDto.getGenderSetting();
        this.isPrivate = roomRequestDto.getIsPrivate();
        this.roomPassword = roomRequestDto.getRoomPassword();
    }
    public void update(RoomRequestDto roomRequestDto) {
        this.title = roomRequestDto.getTitle();
        this.category = roomRequestDto.getCategory();
        this.genderSetting = roomRequestDto.getGenderSetting();
        this.isPrivate = roomRequestDto.getIsPrivate();
        this.roomPassword = roomRequestDto.getRoomPassword();
    }
}