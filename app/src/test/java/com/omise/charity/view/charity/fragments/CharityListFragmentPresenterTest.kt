package com.omise.charity.view.charity.fragments

import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.omise.charity.model.CharitiesResult
import com.omise.charity.model.CharitiesSuccess
import com.omise.charity.model.CharityList
import com.omise.charity.network.HttpCallFailureException
import com.omise.charity.network.NoNetworkException
import com.omise.charity.network.OmiseRepository
import com.omise.charity.network.ServerUnreachableException
import com.omise.charity.util.rx.SchedulersCoupler
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.inOrder
import java.util.concurrent.TimeUnit


class CharityListFragmentPresenterTest {
    private val view: CharityListFragmentView = Mockito.mock(CharityListFragmentView::class.java)
    private val scheduler: SchedulersCoupler = Mockito.mock(SchedulersCoupler::class.java)
    private val api: OmiseRepository = Mockito.mock(OmiseRepository::class.java)
    private val objectUnderTest = CharityListFragmentPresenter(view, api, scheduler)

    @Before
    fun setUp() {
        removeScheduler()
    }

    @Test
    fun `dispose observables on destroy view`() {
        //given
        val disposable = Observable.interval(100, TimeUnit.MILLISECONDS).subscribe()
        objectUnderTest.bindToLifecycle(disposable)
        //when
        objectUnderTest.onViewDestroyed()
        //then
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun `load charities on screen creation, success`() {
        // given
        val charitySuccess = CharitiesSuccess(CharityList(0, listOf()))
        given(api.getCharities()).willReturn(Single.just(charitySuccess))

        // when
        objectUnderTest.onViewCreated()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).show(charitySuccess.charityList)
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `given no network, load charities on screen creation, results failure`() {
        // given
        val exception = NoNetworkException(Throwable("No Network"))
        given(api.getCharities()).willReturn(Single.error(exception))

        // when
        objectUnderTest.onViewCreated()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).displayNoNetworkError()
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `given server unreachable, load charities on screen creation, results failure`() {
        // given
        val exception = ServerUnreachableException(Throwable("Server Unreachable!"))
        given(api.getCharities()).willReturn(Single.error(exception))

        // when
        objectUnderTest.onViewCreated()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).displayServerUnreachableError()
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `given server error, load charities on screen creation, results failure`() {
        // given
        val exception = HttpCallFailureException(Throwable("Http Error!"))
        given(api.getCharities()).willReturn(Single.error(exception))

        // when
        objectUnderTest.onViewCreated()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).displayCallFailedError()
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `load charities on screen reload, success`() {
        // given
        val charitySuccess = CharitiesSuccess(CharityList(0, listOf()))
        given(api.getCharities()).willReturn(Single.just(charitySuccess))

        // when
        objectUnderTest.onRefresh()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).show(charitySuccess.charityList)
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    @Test
    fun `try loading charities on screen reload, failure due to network error`() {
        // given
        val exception = NoNetworkException(Throwable("No Network"))
        given(api.getCharities()).willReturn(Single.error(exception))

        // when
        objectUnderTest.onRefresh()
        // then
        val ordered = inOrder(view)
        ordered.verify(view).refresh = true
        ordered.verify(view).displayNoNetworkError()
        ordered.verify(view).refresh = false
        verifyNoMoreInteractions(view)
    }

    private fun removeScheduler() {
        given(scheduler.convertToAsyncSingle<CharitiesResult>())
            .willReturn(SingleTransformer { it })
    }
}