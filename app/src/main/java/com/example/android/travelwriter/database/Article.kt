package com.example.android.travelwriter.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "drafts")
data class Article(
        @PrimaryKey(autoGenerate = true)
        var id: Long =0L,

        @ColumnInfo(name = "author")
        var author: String = "",

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "body")
        var body: String = ""
): Parcelable