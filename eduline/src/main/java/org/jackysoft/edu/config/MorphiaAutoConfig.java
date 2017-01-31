package org.jackysoft.edu.config;

import java.net.UnknownHostException;
import java.util.Arrays;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.jackysoft.edu.Constants;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

@Configuration
public class MorphiaAutoConfig {

	

	@Bean
	public Datastore datastore() throws UnknownHostException {
		Morphia morphia = new Morphia();
		morphia.mapPackage(Constants.MONGODB_ENTITY_PACKAGE_NAME);


		MongoCredential credential =
				MongoCredential.createCredential(
						Constants.MONGODB_DATABASE_USER,
						Constants.MONGODB_DATABASE_NAME,
						Constants.MONGODB_DATABASE_PWD);
		ServerAddress serverAddress = new ServerAddress(
				Constants.MONGODB_SERVER_HOST,
				Constants.MONGODB_SERVER_PORT);
		MongoClientOptions opts = MongoClientOptions.builder()
				.cursorFinalizerEnabled(true)
				.build();

		MongoClient mongoClient = new MongoClient(
				serverAddress,
				Arrays.asList(credential),
				opts);

		Datastore datastore = morphia.createDatastore(mongoClient, Constants.MONGODB_DATABASE_NAME);

		datastore.ensureIndexes();

		return datastore;

																			
	}

}
