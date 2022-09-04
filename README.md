# Pharmacy Simulator

## Notes
- The possible states of a patient and drugs are implemented as constants in two enumerations.
- The Pharmacy entity is responsible for the administration of drugs on patients and it provides all the rules for state transitions. 
- If the need to add either states or drugs arises, it suffices to add them in the corresponding enumeration, and adding the new rules in the list of rules in the Pharmacy entity.

- The Paracetamol + Aspirin combination results in death for any state so its verified first.
- The Dead state is verified by last, what allows The flying spaghetti monster to possibly show his noodly power, even if the patient was just killed by some drug or lack of it (diabetes without insulin)
- The Healthy state is verified just before the Dead one, allowing the combination Insulin + Antibiotic to create its side effect resulting in Fever, even if the patient was just turned Healthy.

## Built With
- Java 11
- Maven

## Requirements
- Java installed
- JAVA_HOME defined (because of maven wrapper)

## Usage
### Build
```sh
./mvnw clean install

java -jar .\target\pharmacy-simulator-1.jar T,F,D An,I
```