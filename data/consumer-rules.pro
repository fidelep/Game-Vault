# Room Database
-keep class androidx.room.** { *; }
-keep @androidx.room.* class * { *; }
-keep interface androidx.room.** { *; }

# Retrofit (if used)
-keep class retrofit2.** { *; }
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Keep models (if serialized with Gson, Moshi, etc.)
-keep class me.fidelep.gamevault.data.model.** { *; }

-keepattributes Signature
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter {
    *;
}

-keep class retrofit2.** { *; }
-keepclassmembers,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}