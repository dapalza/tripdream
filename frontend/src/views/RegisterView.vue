<template>
  <div>
    <div class="d-flex justify-center">
      <v-typography class="text-h4">회원가입</v-typography>
    </div>
    <v-form>
      <v-container :style="{ maxWidth: `400px` }">
        <v-typography class="text-subtitle-2 font-weight-bold"
          >이메일 주소</v-typography
        >
        <v-text-field
          :loading="loading"
          label="예) dapalza@dapalza.co.kr"
          type="email"
          v-model="email"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography class="text-subtitle-2 font-weight-bold"
          >닉네임</v-typography
        >
        <v-text-field
          :loading="loading"
          label="영어, 한글 조합 00자"
          v-model="username"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography class="text-subtitle-2 font-weight-bold"
          >비밀번호</v-typography
        >
        <v-text-field
          :loading="loading"
          label="영문, 숫자, 특수문자 조합 00 ~ 00자"
          type="password"
          v-model="password"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography class="text-subtitle-2 font-weight-bold"
          >생일</v-typography
        >
        <v-text-field
          :loading="loading"
          label="생일 입력"
          type="number"
          v-model="height"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>

        <v-typography class="text-subtitle-2 font-weight-bold">키</v-typography>
        <v-select
          :loading="loading"
          label="선택하세요"
          v-model="height"
          :items="heightOptions"
          variant="underlined"
          single-line
          required
        ></v-select>

        <v-typography class="text-subtitle-2 font-weight-bold"
          >신발 사이즈</v-typography
        >

        <v-text-field
          :loading="loading"
          label="입력하세요."
          type="number"
          v-model="feet"
          variant="underlined"
          density="compact"
          single-line
          required
        ></v-text-field>
        <v-btn block type="submit" @click.prevent="handleSubmit"
          >가입하기</v-btn
        >
      </v-container>
    </v-form>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import {
  VForm,
  VContainer,
  VTextField,
  VBtn,
  VTypography,
  VSelect,
} from "vuetify";

const apiClient = axios.create({
  baseURL: "http://192.168.35.107:8080",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  components: {
    VForm,
    VContainer,
    VTextField,
    VBtn,
    VTypography,
    VSelect,
  },
  setup() {
    const username = ref("");
    const email = ref("");
    const password = ref("");
    const dob = ref(null);
    const height = ref("");
    const feet = ref("");
    const loading = ref(false);

    const heightOptions = [
      "150cm 미만",
      "150cm ~ 154cm",
      "155cm ~ 159cm",
      "160cm ~ 164cm",
      "165cm ~ 169cm",
      "170cm ~ 174cm",
      "175cm ~ 179cm",
      "180cm ~ 184cm",
      "185cm ~ 189cm",
      "190cm 이상",
    ];

    const handleSubmit = async () => {
      console.log("handleSubmit");

      try {
        loading.value = true;

        await apiClient.post("/register", {
          username: username.value,
          email: email.value,
          password: password.value,
          dob: dob.value,
          height: height.value,
          feet: feet.value,
        });

        // reset form values
        username.value = "";
        email.value = "";
        password.value = "";
        dob.value = null;
        height.value = "";
        feet.value = "";
        loading.value = false;

        alert("@@@ 성공 @@@");
      } catch (error) {
        loading.value = false;
        alert("@@@ 실패 @@@");
      }
    };

    return {
      username,
      email,
      password,
      dob,
      height,
      feet,
      loading,
      heightOptions,
      handleSubmit,
    };
  },
};
</script>

<style></style>
