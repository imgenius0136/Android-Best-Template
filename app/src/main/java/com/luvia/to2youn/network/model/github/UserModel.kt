package com.luvia.to2youn.network.model.github

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.luvia.to2youn.network.model.base.BaseResponse
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserModel(
    @SerializedName("total_count")
    @Expose
    val totalCount: Int,
    @SerializedName("incomplete_results")
    @Expose
    val incompleteResults: Boolean,
    @SerializedName("items")
    @Expose
    val items: ArrayList<UserItem>?
) : Parcelable


@Parcelize
data class UserItem(
    @SerializedName("login")
    @Expose
    val login: String?,
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("node_url")
    @Expose
    val nodeID: String?,
    @SerializedName("avatar_url")
    @Expose
    val avatarURL: String,
    @SerializedName("gravatar_id")
    @Expose
    val gravatarID: String?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("html_url")
    @Expose
    val htmlURL: String?,
    @SerializedName("followers_url")
    @Expose
    val followersURL: String?,
    @SerializedName("following_url")
    @Expose
    val followingURL: String?,
    @SerializedName("gists_url")
    @Expose
    val gistsURL: String?,
    @SerializedName("starred_url")
    @Expose
    val starredURL: String?,
    @SerializedName("subscriptions_url")
    @Expose
    val subscriptionsURL: String?,
    @SerializedName("organizations_url")
    @Expose
    val organizationsURL: String?,
    @SerializedName("repos_url")
    @Expose
    val reposURL: String?,
    @SerializedName("events_url")
    @Expose
    val eventsURL: String?,
    @SerializedName("received_events_url")
    @Expose
    val receivedEventsURL: String?,
    @SerializedName("type")
    @Expose
    val type: String?,
    @SerializedName("site_admin")
    @Expose
    val siteAdmin: Boolean,
    @SerializedName("score")
    @Expose
    val score: Float
) : Parcelable {
    @IgnoredOnParcel
    var sortWord: String = ""
    @IgnoredOnParcel
    var isBookmarked: Boolean = false
    @IgnoredOnParcel
    var position: Int = 0
}