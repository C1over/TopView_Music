package com.hebaiyi.www.topviewmusic.music.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hebaiyi.www.topviewmusic.R;
import com.hebaiyi.www.topviewmusic.bean.Music;
import com.hebaiyi.www.topviewmusic.bean.LocalMusic;
import com.hebaiyi.www.topviewmusic.music.service.MusicManager;

public class BottomFragment extends Fragment implements View.OnClickListener {

    private ImageButton mIbPlay;
    private ImageButton mIbList;
    private ImageView mIvPicture;
    private TextView mTvTitle;
    private TextView mTvSinger;
    private LinearLayout mLlytContainer;
    private boolean isPlaying = false;
    private Music mMusic = new Music();
    private MusicManager mManager = MusicManager.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        mIbPlay = view.findViewById(R.id.bottom_ib_play);
        mIbList = view.findViewById(R.id.bottom_ib_song_list);
        mIvPicture = view.findViewById(R.id.bottom_iv_pic);
        mTvTitle = view.findViewById(R.id.bottom_tv_song_name);
        mTvSinger = view.findViewById(R.id.bottom_tv_singer_name);
        mLlytContainer = view.findViewById(R.id.bottom_llyt_container);
        mIbPlay.setOnClickListener(this);
        mIbList.setOnClickListener(this);
        mIvPicture.setOnClickListener(this);
        mLlytContainer.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_ib_play:
                clickPlayBtn();
                break;
            case R.id.bottom_ib_song_list:
                showList();
                break;
            case R.id.bottom_iv_pic:
                MusicActivity.actionStart(getContext(),getBottomMusic());
                break;
            case R.id.bottom_llyt_container:
                MusicActivity.actionStart(getContext(),getBottomMusic());
                break;
        }
    }

    private void showList(){

    }

    public void setBottomSong(Music music) {
        mMusic.setName(music.getName());
        mMusic.setPicUrl(music.getPicUrl());
        mMusic.setPlaying(music.isPlaying());
        mMusic.setSinger(music.getSinger());
        mMusic.setUrl(music.getUrl());
        mMusic.setPlaying(music.isPlaying());
        mMusic.setLyrics(music.getLyrics());
        isPlaying = music.isPlaying();
        if (music == null) {
            return;
        }
        if (music.getName() != null) {
            mTvTitle.setText(music.getName());
        }
        if (music.getSinger() != null) {
            mTvSinger.setText(music.getSinger());
        }
        if (music.getPicUrl() != null) {
            Glide.with(getActivity().getApplicationContext())
                    .load(music.getPicUrl())
                    .into(mIvPicture);
        }
        if (music.getPicUrl() == null) {
            mIvPicture.setImageResource(R.drawable.search_album_default);
        }
        if (music.isPlaying()) {
            mIbPlay.setImageResource(R.drawable.bottom_playing);
        } else {
            mIbPlay.setImageResource(R.drawable.bottom_play);
        }
    }

    public Music getBottomMusic() {
        mMusic.setPlaying(isPlaying);
        return mMusic;
    }

    private void clickPlayBtn() {
        if (!isPlaying) {
            mIbPlay.setImageResource(R.drawable.bottom_playing);
            mManager.start();
            isPlaying = true;
        } else {
            mIbPlay.setImageResource(R.drawable.bottom_play);
            mManager.pause();
            isPlaying = false;
        }
    }


}
