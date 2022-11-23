package ga.kimdh.testfirebase;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class BusDAO {
    private DatabaseReference databaseReference;

    BusDAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Bus.class.getSimpleName());

    }
    //등록
    public Task<Void> add(Bus bus){
        return databaseReference.push().setValue(bus);
    }
    //조회
    public Query get(){
        return databaseReference;
    }
}
