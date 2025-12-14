ACTIVIDAD 3
Action History Application

Una aplicación añadida a una ya previamente existente de tareas, que te da todo el historial de acciones que se realizan permitiendo ver la fecha y el tipo de acción que se a realizado asi como poder filtrar tanto por tipo como por fecha en la que se hizo la acción.


1.-Cambiar el branch de main a main1
2.-Abre tu terminal o línea de comandos. 
3.-Navega a la carpeta donde quieres guardar el proyecto. 
4.-Clona el proyecto 
5.-Abre Android Studio. 
6.-Selecciona File > Open (Archivo > Abrir). 
7.-Navega a la carpeta donde clonaste el proyecto y selecciona la carpeta raíz (la que contiene los archivos de gradle).
8.-Android Studio automáticamente intentará sincronizar el proyecto con Gradle y aparecera una notificacion para SYNC NOW. 
9.-Seleccionar un dispositivo para ejecutar la aplicacion. 
10.-Haz clic en el botón verde de Run.


public void createAllTables(@NonNull final SQLiteConnection connection) {
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `task` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `taskTittle` TEXT, `taskDescription` TEXT, `createdAt` TEXT, `isCompleted` TEXT)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS `history` (`history_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accion` TEXT, `created_At` TEXT, `details` TEXT)");
        SQLite.execSQL(connection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(connection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '714d39019566af5bea01b94c10dd63e9')");
      }
      

<img width="406" height="895" alt="Captura de pantalla 2025-12-14 153201" src="https://github.com/user-attachments/assets/2324150b-02f1-4f3c-ba55-971b05937d6b" />
<img width="402" height="889" alt="Captura de pantalla 2025-12-14 153502" src="https://github.com/user-attachments/assets/730c8f20-271e-424f-817f-e10edafa4d55" />
<img width="401" height="884" alt="Captura de pantalla 2025-12-14 153227" src="https://github.com/user-attachments/assets/a65c8a30-9bbf-43fb-9f36-ebe993a75766" />
<img width="401" height="890" alt="Captura de pantalla 2025-12-14 153221" src="https://github.com/user-attachments/assets/c966af6f-306d-4f0a-9174-ce04a5f2cc5a" />
<img width="401" height="899" alt="Captura de pantalla 2025-12-14 153215" src="https://github.com/user-attachments/assets/57752203-ef2a-439f-9f70-93383bbd1ef2" />
<img width="403" height="896" alt="Captura de pantalla 2025-12-14 153208" src="https://github.com/user-attachments/assets/1c7cd696-5af9-4c30-b1aa-98cfae1b8702" />

