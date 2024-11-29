# Luca Ricci
# Codice Fiscale API

## 1. Introduzione

Questo progetto è stato sviluppato utilizzando **Spring Boot** con **Java 17**. L'obiettivo principale dell'applicazione è la gestione e la validazione di un **codice fiscale** italiano. Il sistema espone un servizio tramite **API REST** per la validazione del codice fiscale, utilizzando un database temporaneo **H2** in memoria. Poiché l'applicazione è stata progettata per convalidare i codici fiscali senza necessità di persistenza permanente dei dati, non è stato implementato un modello JPA o un repository JPA.

## 2. Motivazione dell'Approccio

### 2.1 Servizio Statico Senza Persistenza Permanente

Nel progetto non è stato implementato un modello o un repository JPA, poiché non c'era la necessità di memorizzare i dati in un database persistente. L'applicazione si concentra sulla logica di business per validare i codici fiscali e restituire i risultati. Non essendo richiesto un sistema di persistenza a lungo termine, è stato scelto di non introdurre un modello e un repository JPA.

### 2.2 Uso di un Database Temporaneo H2

Invece di utilizzare un database esterno, è stato configurato un **database H2 in memoria** per gestire i dati temporaneamente. Questo tipo di database è stato utile durante la fase di sviluppo e testing, in quanto i dati vengono eliminati ogni volta che il server viene riavviato. L'uso di H2 ha permesso di testare la validità del codice fiscale senza la necessità di una connessione a un database esterno.

### 2.3 Nel Controller e Servizio fino al Test su Swagger

Il progetto espone un'API REST tramite il **controller** che consente agli utenti di inviare richieste con un **codice fiscale** e ricevere informazioni sulla **data di nascita** e sull'**età** dell'individuo associato a quel codice fiscale. La logica del codice fiscale viene implementata nel **servizio**, che gestisce la validazione e l'elaborazione dei dati.

#### Logica nel Servizio: CodiceFiscaleService

La logica di elaborazione del codice fiscale è suddivisa in vari passaggi:

1. **Estrazione delle Parti del Codice Fiscale**:
   - Il codice fiscale italiano è composto da diverse parti che rappresentano, tra le altre cose, l'anno di nascita, il mese di nascita e il giorno di nascita.
   - Queste informazioni vengono estratte mediante il metodo `substring`:
     - **Anno di nascita**: Le due cifre che vanno dalla posizione 6 alla 8.
     - **Mese di nascita**: Una lettera che rappresenta il mese (ad esempio: "E" per gennaio).
     - **Giorno di nascita**: Due cifre che rappresentano il giorno.

2. **Calcolo dell'Anno di Nascita**:
   - Il codice fiscale contiene l'anno di nascita con solo due cifre. Per ottenere l'anno completo, viene applicata una logica che aggiunge "20" o "19" a seconda del valore estratto.

3. **Mese di Nascita**:
   - I mesi sono rappresentati da lettere, e viene utilizzata una stringa di riferimento "ABCDEHLMPRST" per decodificare la lettera nel numero del mese (ad esempio, "A" per gennaio, "B" per febbraio).

4. **Giorno di Nascita**:
   - Il giorno estratto dal codice fiscale può essere modificato se il codice fiscale appartiene a una donna, in quanto viene sottratto 40 al giorno per differenziare i codici fiscali maschili e femminili.

5. **Calcolo dell'Età**:
   - Una volta ottenuta la data di nascita, viene calcolata l'età dell'individuo utilizzando la libreria `java.time.Period` che calcola la differenza tra la data di nascita e la data attuale.

6. **Restituzione dei Dati**:
   - Il servizio restituisce una mappa (`Map<String, Object>`) contenente:
     - **"dataNascita"**: La data di nascita calcolata.
     - **"eta"**: L'età calcolata in base alla data di nascita.
   - La mappa viene inviata dal controller come risposta all'utente.

## 3. API Documentation e Swagger UI

L'API è documentata e testabile tramite **Swagger UI**, che è integrato nel progetto grazie alla configurazione di **Springdoc Swagger**. Puoi accedere alla documentazione e testare le chiamate API al seguente URL:

- **API Docs**: `/v3/api-docs`
- **Swagger UI**: `/swagger-ui.html`

### Endpoint disponibile:

- **GET /api/v1/codicefiscale/{codiceFiscale}**
  - **Descrizione**: Restituisce le informazioni sulla data di nascita e l'età associate al codice fiscale fornito.
  - **Parametri**: 
    - `{codiceFiscale}`: Il codice fiscale dell'individuo.
  - **Risposta**: Un oggetto JSON contenente la **data di nascita** e l'**età**.

### Esempio di risposta:
```json
{
  "dataNascita": "1999-03-15",
  "eta": 25
}
