package br.com.myapplication.instagramclonemastertwo.Fragment;


import android.content.Intent;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import br.com.myapplication.instagramclonemastertwo.Activity.EditProfile;
import br.com.myapplication.instagramclonemastertwo.Model.User;
import br.com.myapplication.instagramclonemastertwo.R;
import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
        TextView profilename,profiledesc;
    FirebaseAuth firebaseAuth;
    String name,desc,id;
    DatabaseReference mDatabase;
    CircleImageView profile_image;
    Button editprofile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        profilename=view.findViewById(R.id.profiename);
        profiledesc=view.findViewById(R.id.profiledesc);
        profile_image=view.findViewById(R.id.profile_image);
        editprofile=view.findViewById(R.id.editprofile);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name=user.getEmail();
        id=user.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users").child(id);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                profilename.setText(user.name);
                profiledesc.setText(user.about);
                Picasso.get()
                        .load(user.url)
                        .placeholder(R.drawable.perfil)
                        .error(R.drawable.perfil)
                        .into(profile_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }



}
