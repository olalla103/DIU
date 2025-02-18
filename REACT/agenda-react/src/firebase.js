import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup } from "firebase/auth";
import { getFirestore, doc, getDoc, setDoc } from "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyB6R9ByJy8fn-zOJkL0ZE5C-SvyEarV0Tg",
  authDomain: "pruebalogin-87789.firebaseapp.com",
  projectId: "pruebalogin-87789",
  storageBucket: "pruebalogin-87789.firebasestorage.app",
  messagingSenderId: "357387179150",
  appId: "1:357387179150:web:d7981cf18cd78ac958a5df",
  measurementId: "G-05M9X6BZ4R"
};

// Inicializar Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const firestore = getFirestore(app);

// Proveedor de autenticación de Google
const provider = new GoogleAuthProvider();
export const signInWithGoogle = () => {
  signInWithPopup(auth, provider);
};

// Generar documento de usuario
export const generateUserDocument = async (user, additionalData) => {
  if (!user) return;

  const userRef = doc(firestore, `users/${user.uid}`);
  const snapshot = await getDoc(userRef);

  if (!snapshot.exists()) {
    const { email, displayName, photoURL } = user;
    try {
      await setDoc(userRef, {
        displayName,
        email,
        photoURL,
        ...additionalData
      });
    } catch (error) {
      console.error("Error creating user document", error);
    }
  }
  return getUserDocument(user.uid);
};

// Obtener documento de usuario
const getUserDocument = async uid => {
  if (!uid) return null;
  try {
    const userRef = doc(firestore, `users/${uid}`);
    const userDocument = await getDoc(userRef);
    return {
      uid,
      ...userDocument.data()
    };
  } catch (error) {
    console.error("Error fetching user", error);
  }
};
