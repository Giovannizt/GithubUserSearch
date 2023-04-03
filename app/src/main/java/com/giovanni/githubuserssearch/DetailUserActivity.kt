package com.giovanni.githubuserssearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.giovanni.githubuserssearch.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    companion object{
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text1,
            R.string.tab_text2
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        val userData = intent.getStringExtra(EXTRA_USER)
        binding.tvUserName.text = userData

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = userData.toString()
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        if (userData != null){
            showLoadingDetail(true)
            viewModel.getUserDetail(userData)
            showLoadingDetail(false)
        }

        viewModel.userDetail.observe(this, {detailUser ->
            setUserDetail(detailUser)
        })

        viewModel.isLoadingUser.observe(this,{showLoadingDetail(it)})
    }

    fun setUserDetail(detail: DetailUserResponse){
        Glide.with(this@DetailUserActivity)
            .load(detail.avatarUrl)
            .into(binding.userImage)
        binding.tvUserName.text = detail.name
        binding.username.text = detail.login
        binding.tvFollowers.text = buildString {
        append(detail.followers.toString())
        append(" Followers")
        }
        binding.tvFollowing.text = buildString {
        append(detail.following.toString())
        append(" Following")
    }
    }

    fun showLoadingDetail(isLoading: Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}