package com.thekarlo95.search.ui.andorid.respositorysearch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.thekarlo95.search.ui.andorid.R
import com.thekarlo95.search.ui.andorid.respositorysearch.model.RepositoryUiModel
import java.text.DateFormat

class RepositoryAdapter(
        private var itemOnClick: (owner: String, repository: String) -> Unit,
        private var imageOnClick: (owner: String) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositories: List<RepositoryUiModel> = listOf()

    operator fun plusAssign(newRepositories: List<RepositoryUiModel>) {
        repositories += newRepositories
    }

    fun clear() {
        repositories = listOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(itemView, itemOnClick, imageOnClick)
    }

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(repositories[position])

    class ViewHolder(
            itemView: View,
            private val itemOnClick: (owner: String, repository: String) -> Unit,
            private var imageOnClick: (owner: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        companion object {
            @JvmField
            val DATE_FORMAT: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
        }

        private val llRepositoryInfo: LinearLayout = itemView.findViewById(R.id.ll_repository_info)
        private val ivRepositoryImage: ImageView = itemView.findViewById(R.id.iv_repository_image)
        private val tvUsernameAndRepositoryName: TextView = itemView.findViewById(R.id.tv_username_repository)
        private val tvStars: TextView = itemView.findViewById(R.id.tv_stars)
        private val tvForks: TextView = itemView.findViewById(R.id.tv_forks)
        private val tvLastChanged: TextView = itemView.findViewById(R.id.tv_last_changed)
        private val tvLanguage: TextView = itemView.findViewById(R.id.tv_language)

        fun bind(repository: RepositoryUiModel): Unit = with(repository) {
            llRepositoryInfo.setOnClickListener {
                itemOnClick(ownerUserName, name)
            }
            tvUsernameAndRepositoryName.text = fullName
            tvStars.text = starsCount.toString()
            tvForks.text = forksCount.toString()
            tvLastChanged.text = DATE_FORMAT.format(updatedAt)
            tvLanguage.text = language
            Glide.with(itemView.context).load(userAvatarUrl).into(ivRepositoryImage)
            ivRepositoryImage.setOnClickListener { imageOnClick(ownerUserName) }
        }
    }
}