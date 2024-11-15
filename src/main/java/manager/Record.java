package manager;

import java.util.ArrayList;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import accesoDatos.Reader;
import accesoDatos.Writer;
import objects.HistoricalRecord;
import objects.Usuario;

public class Record {

	/**
	 * it returns a list of historical record of a particular
	 * @return
	 * @throws Exception
	 */
	public ArrayList<HistoricalRecord>getAllRecords() throws Exception {
		ArrayList<HistoricalRecord> recordsList= new ArrayList<HistoricalRecord>();
		ArrayList<DocumentReference> newRecordsDoc= new ArrayList<DocumentReference>();
		HistoricalRecord newRecord =  new HistoricalRecord();

		Map<String, Object> miUsuarioMap = new Reader().getCurrentUserDataMap();
		
		if(miUsuarioMap.get("wk_history") instanceof ArrayList){
			newRecordsDoc = (ArrayList<DocumentReference>) miUsuarioMap.get("wk_history");
			;}
		else if(miUsuarioMap.get("wk_history") instanceof DocumentReference){
			newRecordsDoc.add((DocumentReference) miUsuarioMap.get("wk_history"));
			;}
		else {
				System.out.println("Not any historical record yet");
				//in case of producing failure, it could be loaded a record with descriptive name
		}
		
		for(DocumentReference recordRef:  newRecordsDoc) {
			recordsList.add(new Reader().getOneHistoricalRecord(recordRef));
			
		}
		
		return recordsList;
	}
	
	
	public Usuario getOneUsuario() throws Exception {
		ArrayList<DocumentReference> newRecordsDoc= null;
		Map<String, Object> miUsuarioMap = new Reader().getCurrentUserDataMap();
		//this new record should be buildbeforehand
		HistoricalRecord newRecord =  new HistoricalRecord();
		Usuario usuario = new Usuario();
		
		
		Long longValue = (Long) miUsuarioMap.get("level"); 
		Integer miLevel = longValue.intValue();  	
		DocumentReference nextWorkout = new Reader().getOneLevelHigherWkRef(miLevel);
		
		//ArrayList<HistoricalRecord> records = new ArrayList<HistoricalRecord>();
		if(miUsuarioMap.get("wk_history") instanceof ArrayList){
			newRecordsDoc = (ArrayList<DocumentReference>) miUsuarioMap.get("wk_history");
			newRecordsDoc.add(new Writer().addHistoricalRecord(newRecord))	;}
		else {
			newRecordsDoc = new ArrayList<DocumentReference>();
			newRecordsDoc.add(new Writer().addHistoricalRecord(newRecord))	;
		}
		new Writer().updateUserRecords(newRecordsDoc, nextWorkout, miLevel);
		
		


		return usuario;
	}

	public int getRecordsTotalTime() {
int num =0;		

for (HistoricalRecord record: Login.currentUser.getWk_history())
{
	num+=record.getTotalTime();
}
return num;
	}

	
	
	public String getRecordText() {
String text =null;		

for (HistoricalRecord record: Login.currentUser.getWk_history())
{
	text+=record.toString() + "\n";
}
return text;
	}


	public Double getRecordsAcomplishment() throws Exception {
	
		
		int availableWorkout = new Reader().getAllWorkoutsFirebase().size();
		int completedWrokouts = getAllRecords().size();
		
		return (double) (completedWrokouts / availableWorkout);
		
}}
		
/*
//		if (document.exists()) {
//			/*
//			 * usuario.setId(document.getId()); usuario.setName(document.getString("name"));
//			 * usuario.setSurname(document.getString("surname"));
//			 * usuario.setEmail(document.getString("email"));
//			 * usuario.setLevel(document.getDouble("level").intValue());
//			 * usuario.setProgress(document.getDouble("progress").intValue());
//			 * usuario.setTrainer(document.getBoolean("isTrainer"));
//			 * usuario.setRegistered(document.getBoolean("registered"));
//			 * usuario.setWorkout(null);
//			 */
//			// This one should work, but we weed all the data to set up a new record
//			// recordsDoc = (ArrayList<DocumentReference>)
//			// document.getData().get("wk_history");
//			//
//
//			for (Map.Entry<String, Object> entry : miUsuarioMap.entrySet()) {
//				System.out.println(entry.getKey() + " => " + entry.getValue() + " " + entry.getValue().getClass());
//
//				if (entry.getKey().equalsIgnoreCase("wk_history")) {
//					recordsDoc = (ArrayList<DocumentReference>) entry.getValue();
//				}
//			}
//
//		} else {
//			System.out.println("No such usuario found!");
//		}
//
//		for (DocumentReference doc : refSetsDoc) {
//			refSets.add(getSets(doc));
//
//		}