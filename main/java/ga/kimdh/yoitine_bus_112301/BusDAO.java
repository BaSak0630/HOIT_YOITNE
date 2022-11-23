package ga.kimdh.yoitine_bus_112301;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    //수정
    public Task<Void> update(String key, HashMap<String, Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
}
