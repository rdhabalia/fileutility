
# What is FileToObject

Filob is the tool which can process a huge file (.txt/XML/EDI) in a sync/Async mode. It's simple API provides a quick way to convert any File-format to defined POJO.

It splits file into chunks of the file so, chunks of the file can be processed individually and asynchronously.

It can also separate out Meta and Data file out of huge file. So, Meta file can be shared across the process of Data file.

# How to Use it:

1. checkout project
2. Run > com.Filob.file.test.reader.TestUserFile.java
3. See result on console.

FileToObject-coverter api requires following things:

1.	Define source location of file.
2.	How to recognize record from the file : i.e: Define Specific pattern to distinguish Meta and Data content
3.	Define appropriate POJO to transform raw record into Java object.
4.	Define processor to process POJO.

Example:

1.User.txt
Here, we have raw_data_file: user.txt. 

It has been divided in 3 different things
a.	Header: it describes category of the user
b.	userData: It describes user detail
c.	Summary: it describe total record of the data.

Here, Header and Summary are metadata of file and it can be shared by all data_records of the userData.

```
User.txt

H~Kids
D~James~Smith~000-000-0000~CA~12340~USA
D~James1~Smith1~000-000-0001~CA~12341~USA
D~James2~Smith2~000-000-0002~CA~12342~USA
D~James3~Smith3~000-000-0003~CA~12343~USA
D~James4~Smith4~000-000-0004~CA~12344~USA
D~James5~Smith5~000-000-0005~CA~12345~USA
D~James6~Smith6~000-000-0006~CA~12346~USA
D~James7~Smith7~000-000-0007~CA~12347~USA
D~James8~Smith8~000-000-0008~CA~12348~USA
D~James9~Smith9~000-000-0009~CA~12349~USA
S~10
```

2.POJO
Now, we will create 3 different POJO to store raw records into Java record.
```
com.fileobj.UserData.java
com.fileobj.UserMeta.java
com.fileobj.UserSumamry.java
```

4.Test file to configure setting and start process: com.fileobj.TestUserFile.java

```
public void process()
    {
//Define Unique ProcessId
    	FileExecutor executor = new LineExecutor();
    	
    	Config config = new Config();
    	//Define Source Path
    	config.setSrcPath("user.txt");
    	config.setOutputPath("target2");
    	config.setSrcMIMEType("txt");
		
    	
    	//[1]Meta
    	ClassClassificationConfig classification1 = new ClassClassificationConfig();
    	RecordClassifier classifier1 = new UserCustomRecordClassifier();
		//Define Pattern to identify header: i.e: H~Kids
    	classifier1.getPatterns().add("H~");
    	classification1.setClassifier(classifier1);
    	classifier1.setClassifierName("meta");
    	classifier1.setMetaData(true);
		//Define POJO
    	classification1.setClassName("com.fileobj.UserMeta");
		//Define records per file (each split file will contain following records)
    	//Delimiter: i.e: H~Kids
    	classification1.getRecordBreaker().getDelimiters().add("~");
    	classification1.setReturnEmptyField(true);
		//Define processor
    	classification1.setProcessor(new CustomUserProcessor());


    	//[2] Data
    	ClassClassificationConfig classification2 = new ClassClassificationConfig();
    	RecordClassifier classifier2 = new UserCustomRecordClassifier();
    	classifier2.getPatterns().add("D~");
    	classification2.setClassifier(classifier2);
    	classifier2.setClassifierName("data");
    	classification2.setClassName("com.fileobj.UserData");
		//Define records per file (each split file will contain following records)
    	classification2.getRecordBreaker().getDelimiters().add("~");
    	classification2.setReturnEmptyField(true);
    	classification2.setProcessor(new CustomUserProcessor());


    	//[3] Summary
    	ClassClassificationConfig classification3 = new ClassClassificationConfig();
    	RecordClassifier classifier3 = new UserCustomRecordClassifier();
    	classifier3.getPatterns().add("S~");
    	classification3.setClassifier(classifier3);
    	classifier3.setClassifierName("summary");
    	classifier3.setMetaData(true);
    	classification3.setClassName("com.fileobj.UserSummary");
    	classification3.getRecordBreaker().getDelimiters().add("~");
    	classification3.setReturnEmptyField(true);
    	classification3.setProcessor(new CustomUserProcessor());
    	
    	
    	List<ClassClassificationConfig> classificationConfigs = new ArrayList<ClassClassificationConfig>();
    	classificationConfigs.add(classification1);
    	classificationConfigs.add(classification2);
    	classificationConfigs.add(classification3);
    	
    	try {
			//Start Process
			executor.build(config, classificationConfigs, executor.split(config, classificationConfigs));
		} catch (ProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
```
